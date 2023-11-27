import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import React from "react";

// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import 'swiper/css';
import "swiper/css/navigation";

import "../App.css";

// import required modules
import { Navigation } from "swiper";

import { ReactComponent as More } from "../assets/more.svg";

function Best({ flatform, flatformname }) {
  const [dramas, setDramas] = useState([]);

  useEffect(() => {
    // const apiUrl = "http://belleravi.co.kr/api/netflex";
    const apiUrl = "https://belleravi.co.kr/api/" + flatform;

    const fetchDramas = async () => {
      try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error("데이터를 가져오는 중에 오류가 발생했습니다!");
        }
        const data = await response.json();
        const top10dramas = data.slice(0, 10);

        setDramas(top10dramas);
      } catch (e) {
        console.error("Error during fetch: " + e);
      }
    };
    fetchDramas();
  }, [flatform]);

  return (
    <div className="best-list">
      <div className="best-title">
        <h3>
          <div>{flatformname}</div>
          <div>Best</div>
        </h3>
        <More width="20" height="20" className="btn-more" />
      </div>
      <>
        <Swiper
          slidesPerView={6}
          loop={true}
          keyboard={{
            enabled: true,
          }}
          navigation={true}
          modules={[Navigation]}
          className="mySwiper">
          {dramas.map((drama) => (
            <SwiperSlide key={drama.id}>
              <Link to={`/drama/${drama.id}`}>
                <img
                  src={`https://image.tmdb.org/t/p/w200/${drama.poster_path}`}
                  alt={drama.name}
                />
              </Link>
            </SwiperSlide>
          ))}
        </Swiper>
      </>
    </div>
  );
}

export default Best;
