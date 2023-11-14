import React from "react";
import { NavLink, useNavigate } from "react-router-dom";

const Menu = () => {
  const navigate = useNavigate();

  const handleMyPageClick = () => {
    // 로컬 스토리지에서 토큰 확인
    const token = localStorage.getItem('token');

    // 토큰이 없다면 로그인 페이지로 리디렉션
    if (!token) {
      navigate('/login');
    } else {
      navigate('/mypage');
    }
  };

  return (
    <>
      <ul className="menus">
        <NavLink to="/" activeClassName="active">
          <li className="menu home">홈</li>
        </NavLink>
        <NavLink to="/search" activeClassName="active">
          <li className="menu search">검색</li>
        </NavLink>
        <NavLink to="/community" activeClassName="active">
          <li className="menu community">커뮤니티</li>
        </NavLink>
        <li className="menu my" onClick={handleMyPageClick}>마이페이지</li>
      </ul>
    </>
  );
};

export default Menu;
