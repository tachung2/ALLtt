import "./Comnu.css";
import React from "react";
export default function Commu() {
  return (
    <div className="body">
      <div className="container">
        <div className="tcommunity">커뮤니티</div>
        <div className="box">
          <div className="box1">
            <div className="box1-content">
              <br></br>
              User1234
              <br></br>
              <br></br>
              강풀 작가표 무빙 일러스트 포스터 공개됨!!<br></br>
              <img className="moving-img" src="img/moving-img.png" alt="img" />
              <br></br>
              최후의 대결을 그림 한 장에 담았다...<br></br>
              <div className="likecomment">
                <div className="likebox">
                  <img className="like" src="img/like.png" alt="like" /> 125
                </div>
                <div className="commentbox">
                  <img className="like" src="img/comment.png" alt="comment" />{" "}
                  13
                </div>
              </div>
            </div>
          </div>
          <div className="box2">
            <div className="box2-content">
              <br></br>
              Moviestar01
              <br></br>
              <br></br>
              다들 부산국제영화제 예매 성공셨나요?<br></br>
              <div className="likecomment">
                <div className="likebox">
                  <img className="like" src="img/like.png" alt="like" /> 10
                </div>
                <div className="commentbox">
                  <img className="like" src="img/comment.png" alt="comment" />{" "}
                  59
                </div>
              </div>
            </div>
          </div>
          <div className="box3">
            <div className="box3-content">
              <br></br>
              Megabox01
              <br></br>
              <br></br>
              메가박스 냥사원쿠폰<br></br>
              <img className="mega" src="img/mega.png" alt="mega" />
              <br></br>
              23.09.27 - 23.10.03 상영영화에 쓸 수 있는 쿠폰이에요!<br></br>
              다들 즐거운 추석 보내세요~
              <div className="likecomment">
                <div className="likebox">
                  <img className="like" src="img/like.png" alt="like" /> 16
                </div>
                <div className="commentbox">
                  <img className="like" src="img/comment.png" alt="comment" />{" "}
                  30
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
