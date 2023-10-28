import React, {useState} from "react";
import "./Login.css";
import { Link, useNavigate } from "react-router-dom";
import {useAuth} from "../AuthContext";

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { setIsLoggedIn } = useAuth();

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault(); // 기본 폼 제출 동작을 방지

        const payload = {
            email: email,
            password: password,
        };

        try {
            const response = await fetch('http://belleravi.co.kr/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
            });

            const data = await response.json();
            if (response.ok) {
                // 로그인 성공 처리 (예: 토큰 저장, 리다이렉트 등)
                localStorage.setItem('token', data.token); // JWT 토큰 로컬 스토리지에 저장
                setIsLoggedIn(true); // 인증 상태 변경
                navigate('/'); // 메인 페이지로 리다이렉트
            } else {
                // 로그인 실패 처리 (예: 오류 메시지 표시)
                console.error(data.message);
            }
        } catch (error) {
            console.error('로그인 중 오류 발생:', error);
        }
    };

  return (
    <div className="Login">
      <p className="login1">로그인</p>
      <div className="vertical-align">
          <form onSubmit={handleSubmit}>
              <input className="l_user-id" name="email" placeholder="아이디(이메일)"  value={email}
                     onChange={(e) => setEmail(e.target.value)}></input>
              <input
                className="l_password"
                type="password"
                name="password"
                placeholder="비밀번호"
                value={password}
                onChange={(e) => setPassword(e.target.value)}></input>
              <button className="l_login-button" >로그인</button>
          </form>
      </div>
      <div className="find">
        <p className="id-find">아이디 찾기</p>
        <p className="test1">|</p>
        <p className="pw-find">비밀번호 찾기</p>
      </div>
      <div className="link-signup">
        <p className="ment">회원이 아니신가요?</p>
        <Link to={`/signup`}>
          <p className="go-signup">지금 가입하세요</p>
        </Link>
      </div>
    </div>
  );
}

export default Login;
