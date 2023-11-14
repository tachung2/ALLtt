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
    const response = await fetch('http://belleravi.co.kr/api/user-info', {
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
  const [userName, setUserName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFlatformModalOpen, setIsFlatformModalOpen] = useState(false);

  useEffect(() => {
    async function getUserInfo() {
      const userInfo = await fetchUserName();
      if (userInfo && userInfo.userName) {
        setUserName(userInfo.userName);
      }
    }

    getUserInfo();
  }, []);

  const logout = () => {
    window.location.href = "/";
    localStorage.clear();
    setIsLoggedIn(false);
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
          {["netflix", "tving", "wavve", "disney", "watcha", "apple"].map(
            (flatform) => (
              <div
                key={flatform}
                className={`flatform-day flatform-${flatform}`}>
                <div className="justbox1">
                    <img src={`/img/${flatform}.png`} alt={flatform} className="logo-image" onClick={() => setIsFlatformModalOpen(true)}/>
                    <FlatformModal isOpen={isFlatformModalOpen} onClose={() => setIsFlatformModalOpen(false)} />
                </div>


              </div>
            )
          )}
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
