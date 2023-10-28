import React from "react";
import "./Mypage.css";
import {useAuth} from "../AuthContext";

function Mypage() {
  const { setIsLoggedIn } = useAuth();
  const logout = () => {
    localStorage.clear();
    setIsLoggedIn(false);
    window.location.href = "/";
  };

  return (
    <div className="Mypage">
      <section className="mypage-container">
        <div className="user">
          <img className="user-profile" src="" alt="user profile" />
          <div className="about-user">
            <p className="user-name">User1</p>
            <div className="user-sets">
              <button className="btn-user btn-user-modify">내 정보 수정</button>
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
                <div className="justbox1"></div>
              </div>
            )
          )}
        </div>
        <div className="mypage-likes">
          <h2 className="mypage-likes-title">좋아하는 컨텐츠</h2>
          <div className="mypage-likes-list">
            <div className="mypage-likes-item">
              <div className="justbox2"></div>
              <img src="" alt="likes item" />
            </div>
            <div className="mypage-likes-item">
              <div className="justbox2"></div>
              <img src="" alt="likes item" />
            </div>
            <div className="mypage-likes-item">
              <div className="justbox2"></div>
              <img src="" alt="likes item" />
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Mypage;
