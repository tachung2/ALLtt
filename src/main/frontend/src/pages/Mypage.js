import React, { useState, useEffect } from 'react';
import "./Mypage.css";
import {useAuth} from "../AuthContext";
import UserEditModal from "../modal/UserEditModal";
import FlatformModal from "../modal/FlatformModal";

async function fetchUserName() {
  const token = localStorage.getItem('token'); // 로컬 스토리지에서 토큰을 가져옵니다.
  if (!token) {
    console.log("No token found");
    return;
  }

  try {
    const response = await fetch('https://belleravi.co.kr/api/user-info', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });

    if (!response.ok) {
      throw new Error(`Error! status: ${response.status}`);
    }
    const result = await response.json();
    return result;
  } catch (error) {
    console.log(error);
  }
}

function Mypage() {
  const { setIsLoggedIn } = useAuth();
  const [ottData, setOttData] = useState({});
  const [userId, setUserId] = useState(''); // [상태 변수, 상태 변화 함수
  const [userName, setUserName] = useState('');
  const [activeFlatform, setActiveFlatform] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFlatformModalOpen, setIsFlatformModalOpen] = useState(false);

  useEffect(() => {
    async function getUserInfo() {
      const userInfo = await fetchUserName();
      if (userInfo) {
        setUserName(userInfo.userName);
        setUserId(userInfo.id);
        fetchOttData(userInfo.id);
      }
    }

    // OTT 데이터 가져오는 함수
    const fetchOttData = async (userId) => {
      try {
        const response = await fetch(`https://belleravi.co.kr/api/service-dates?userId=${userId}`);
        if (!response.ok) {
          throw new Error(`Error! status: ${response.status}`);
        }
        const data = await response.json();
        const ottObject = data.reduce((obj, item) => {
          obj[item.servicename] = item.servicedate;
          return obj;
        }, {});
        setOttData(ottObject);
      } catch (error) {
        console.log(error);
      }
    };

    getUserInfo();
  }, []);

  // 남은 일자 계산 함수
  const calculateDaysLeft = (serviceDate) => {
    if (!serviceDate) return '';
    const startDate = new Date(serviceDate);
    startDate.setHours(0, 0, 0, 0);
    // 구독 만료 날짜는 시작 날짜로부터 30일 후
    const expirationDate = new Date(startDate);
    expirationDate.setDate(expirationDate.getDate() + 30);
    expirationDate.setHours(0, 0, 0, 0);

    // 현재 날짜
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    // 만료 날짜와 현재 날짜 사이의 남은 시간 (밀리초 단위)
    const remainingTime = expirationDate - today;
    console.log(expirationDate);
    console.log(remainingTime / (1000 * 60 * 60 * 24));
    // 남은 일수 계산
    const remainingDays = Math.ceil(remainingTime / (1000 * 60 * 60 * 24));

    // 남은 일수가 0보다 크면 남은 일수 반환, 그렇지 않으면 '기한 만료' 반환
    return remainingDays > 0 ? `${remainingDays}일 남음` : '기한 만료';
  };


  const logout = () => {
    window.location.href = "/";
    localStorage.clear();
    setIsLoggedIn(false);
  };

  const openFlatformModal = (flatform) => {
    setActiveFlatform(flatform);
    console.log(userId);
    setIsFlatformModalOpen(true);
  };

  const closeFlatformModal = () => {
    setActiveFlatform(null);
    setIsFlatformModalOpen(false);
  };


  return (
    <div className="Mypage">
      <section className="mypage-container">
        <div className="user">
          <img className="user-profile" src="" alt="user profile" />
          <div className="about-user">
            {userName ? <p className="user-name">{userName}</p>: <p className="user-name">Loading...</p>}
            <div className="user-sets">
              <button className="btn-user btn-user-modify" onClick={() => setIsModalOpen(true)}>내 정보 수정</button>
              <UserEditModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} />
              <button className="btn-user btn-user-uploaded">
                게시물 관리
              </button>
              <button className="btn-user btn-user-logout" onClick={logout}>로그아웃</button>
              <button className="btn-user btn-user-signout">탈퇴</button>
            </div>
          </div>
        </div>
        <div className="flatform-days">
          {["netflix", "tving", "wavve", "disney", "watcha", "apple"].map((flatform) => (
              <div key={flatform} className={`flatform-day flatform-${flatform}`}>
                <div className="justbox1">
                  <h1 className="flatform-day-title">{calculateDaysLeft(ottData[flatform])}</h1>
                  <img src={`/img/${flatform}.png`} alt={flatform} className="logo-image" onClick={() => openFlatformModal(flatform)}/>
                </div>
              </div>
            ))}
          <div>
            {isFlatformModalOpen && <FlatformModal isOpen={isFlatformModalOpen} onClose={closeFlatformModal} flatform={activeFlatform} userId={userId}/>}
          </div>
        </div>
        <div className="mypage-likes">
          <h2 className="mypage-likes-title">좋아하는 컨텐츠</h2>
          <div className="mypage-likes-list">
            {/*<div className="mypage-likes-item">*/}
            {/*  <div className="justbox2"></div>*/}
            {/*  <img src="" alt="likes item" />*/}
            {/*</div>*/}
          </div>
        </div>
      </section>
    </div>
  );
}

export default Mypage;
