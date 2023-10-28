import React, { Component } from "react";
import Logo from "./Logo";
import Menu from "./Menu";
import Loginsignup from "./Loginsignup";

// rsf
class Header extends Component {
  render() {
    return (
      <section className="header">
        <Logo />
        <Menu />
        <Loginsignup />
      </section>
    );
  }
}

export default Header;
