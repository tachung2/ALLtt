import React from "react";
import { NavLink } from "react-router-dom";

const Menu = () => {
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
        <NavLink to="/mypage" activeClassName="active">
        <li className="menu my">마이페이지</li>
        </NavLink>
      </ul>
    </>
  );
};

export default Menu;
