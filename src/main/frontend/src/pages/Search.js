import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import "./Search.css";

export default function Search() {
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState("");

  const handleKeyPress = (e) => {
    if (e.key === "Enter" && searchTerm) {
      navigate(`/search-results?query=${searchTerm}`);
    }
  };

  return (
    <div className="body">
      <div className="continer">
        <div className="search">
          <input
            type="text"
            placeholder="검색"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            onKeyPress={handleKeyPress}
          />
        </div>
        <div className="recent-search">
          <div>최근 검색어</div>
          <br></br>
          <ul className="recent-words">
            <li>#무빙</li>
            <li>#7인의탈출</li>
          </ul>
          <br></br>
        </div>
        <div className="line"></div>
        <div className="popular-search">
          <div>인기 검색어</div>
          <br></br>
          <ul className="popular-words">
            <li>1. 무빙</li>
            <li>2. 강철부대3</li>
            <li>3. 바비</li>
            <li>4. 밀수</li>
            <li>5. 마스크걸</li>
          </ul>
          <br></br>
        </div>
        <div className="advertising-banner">
          <img src="img/advertising-banner.png" alt="advertising banner" />
        </div>
      </div>
    </div>
  );
}
