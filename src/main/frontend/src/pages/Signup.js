import React, {useState} from "react";
import "./Signup.css";
import { Link, useNavigate } from "react-router-dom";

function Signup() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const payload = {
      email: email,
      password: password,
      name: name,
    };

    try {
      const response = await fetch('http://belleravi.co.kr/api/signup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        // 회원가입 성공 처리
        navigate('/login'); // 로그인 페이지로 리다이렉트
      } else {
        const data = await response.json();
        // 회원가입 실패 처리 (예: 오류 메시지 표시)
        console.error(data.message);
      }
    } catch (error) {
      console.error('회원가입 중 오류 발생:', error);
    }
  };

  return (
    <div className="Signup">
      <p className="signup2">회원가입</p>
      <div className="vertical-align">
        <form onSubmit={handleSubmit}>
          <input className="user-id" placeholder="아이디(이메일)" name="email"
                 onChange={(e) => setEmail(e.target.value)}
          value={email}></input>
          <input
            className="password"
            type="password"
            placeholder="비밀번호"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          ></input>
          <input
            className="re-password"
            type="password"
            placeholder="비밀번호 확인"></input>
          <input className="name1" type="text" placeholder="이름" name="name" value={name}
                 onChange={(e) => setName(e.target.value)}></input>
          <input className="phone" placeholder="휴대폰 번호"></input>
          <div className="box11">
            <div className="checkbox-container">
              <input
                type="checkbox"
                id="my-checkbox"
                className="checkbox1"
              ></input>
              <label htmlFor="my-checkbox" className="label1">
                [필수] 만 14세 이상입니다.
              </label>
            </div>
            <div className="checkbox-container">
              <input
                type="checkbox"
                id="my-checkbox"
                className="checkbox2"></input>
              <label htmlFor="my-checkbox" className="label1">
                [필수] 사이트 이용약관 동의
              </label>
            </div>
            <div className="checkbox-container">
              <input
                type="checkbox"
                id="my-checkbox"
                className="checkbox3"></input>
              <label htmlFor="my-checkbox" className="label1">
                [필수] 전자금융거래 이용약관 동의
              </label>
            </div>

            <div className="checkbox-container">
              <input
                type="checkbox"
                id="my-checkbox"
                className="checkbox4"></input>
              <label htmlFor="my-checkbox" className="label1">
                [필수] 개인정보 수집 및 이용 동의
              </label>
            </div>
            <div className="checkbox-container">
              <input
                type="checkbox"
                id="my-checkbox"
                className="checkbox5"></input>
              <label htmlFor="my-checkbox" className="label1">
                [필수] 개인정보 제3자 제공 동의
              </label>
            </div>
          </div>

          <button className="login-button">회원가입</button>
        </form>
      </div>

      <div className="link-login">
        <p className="ment1">회원이신가요?</p>
        <Link to={`/login`}>
          <p className="go-login">로그인하기</p>
        </Link>
      </div>
    </div>
  );
}

export default Signup;
