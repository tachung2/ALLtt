import React from "react";
import { NavLink } from "react-router-dom";
import { useAuth } from '../AuthContext';

function Loginsignup() {
    const { isLoggedIn } = useAuth();

    if (isLoggedIn) {
        return (
            <ul className="loginsignup">
                <li className="login"> </li>
                <li className="signup"> </li>
            </ul>
        );
    } else {
        return (
            <ul className="loginsignup">
                <NavLink to="/login">
                    <li className="login">로그인</li>
                </NavLink>
                <NavLink to="/signup">
                    <li className="signup">회원가입</li>
                </NavLink>
            </ul>
        );
    }
}

export default Loginsignup;
