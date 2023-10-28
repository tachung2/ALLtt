import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./DramaDetail.css";

function DramaDetail() {
  const { id } = useParams();
  const [drama, setDrama] = useState(null);
  const [video, setVideo] = useState(null);

  useEffect(() => {
    const dramaApiUrl = `http://belleravi.co.kr/api/detail/${id}`;
    const videoApiUrl = `http://belleravi.co.kr/api/detail/videos/${id}`;

    const fetchDramaDetail = async () => {
      try {
        const response = await fetch(dramaApiUrl);
        if (!response.ok) {
          throw new Error(
            "드라마 상세 정보를 가져오는 중에 오류가 발생했습니다."
          );
        }
        const data = await response.json();
        setDrama(data);
      } catch (error) {
        console.error(error);
      }
    };

    const fetchVideoDetail = async () => {
      try {
        const response = await fetch(videoApiUrl);
        if (!response.ok) {
          throw new Error("비디오 정보를 가져오는 중에 오류가 발생했습니다.");
        }
        const data = await response.json();
        setVideo(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchDramaDetail();
    fetchVideoDetail();
  }, [id]);

  if (!drama || !video) {
    return <div>Loading...</div>;
  }

  function chunkArray(arr, chunkSize) {
    const chunkedArray = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
      chunkedArray.push(arr.slice(i, i + chunkSize));
    }
    return chunkedArray;
  }

  return (
    <div className="detail">
      <div className="description">
        <div className="left-column">
          <h1 className="name">{drama.name}</h1>
          <p className="overview">{drama.overview}</p>
          <p className="popular">인기도: {drama.popularity}</p>

          <div className="total-box">
            <div className="left-box">
              <div className="Dbox1">
                <p className="date1">공개일: </p>
                <p className="date2">{drama.first_air_date}</p>
              </div>
              <div className="Dbox2">
                <p className="number1">부작: </p>
                <p className="number2">{drama.number_of_seasons}</p>
              </div>

              <div className="Dbox4">
                <p className="adult1">성인등급: </p>
                <p className="adult2">{drama.adult} </p>
              </div>
            </div>

            <div className="right-box">
              <div className="Dbox3">
                <p className="generes1">장르: </p>
                <ul>
                  {drama.genres.map((genre) => (
                    <li key={genre.name}>{genre.name}</li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
          <div className="networks">
            <h2>OTT</h2>
            {chunkArray(drama.networks, 3).map((networkGroup, index) => (
              <ul className="network-list" key={index}>
                {networkGroup.map((network) => (
                  <li key={network.name}>
                    <div className="network-item">
                      <img
                        src={`https://image.tmdb.org/t/p/w200${network.logo_path}`}
                        alt={network.name}
                      />
                      <p className="network-name">{network.name}</p>
                    </div>
                  </li>
                ))}
              </ul>
            ))}
          </div>
        </div>
        <div className="right-column">
          <img
            src={`https://image.tmdb.org/t/p/w500/${drama.poster_path}`}
            alt={drama.name}
            className="poster"
          />
        </div>
      </div>

      <div className="created">
        <p className="actor">출연진 & 감독</p>
        <ul className="actor-list">
          {chunkArray(drama.created_by, 5).map((actorGroup, index) => (
            <li key={index} className="actor-row">
              {actorGroup.map((person) => (
                <div key={person.name} className="actor-item">
                  <img
                    src={`https://image.tmdb.org/t/p/w200${person.profile_path}`}
                    alt={person.name}
                  />
                  <p className="actor-name">{person.name}</p>
                </div>
              ))}
            </li>
          ))}
        </ul>
      </div>

      <div className="videos">
        <p className="video-text">VIDEOS</p>
        <ul>
          {video.results.map((video) => (
            <iframe
              title="Video Player"
              width="420"
              height="280"
              src={`https://www.youtube.com/embed/${video.key}`}
              allowFullScreen></iframe>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default DramaDetail;
