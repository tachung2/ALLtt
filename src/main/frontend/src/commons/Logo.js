import React, { Component } from "react";
import { NavLink } from "react-router-dom";

class Logo extends Component {
  render() {
    return (
      <div className="logo">
        <NavLink to="/" activeClassName="active">
          <h1>ALLtt</h1>
        </NavLink>
      </div>
    );
  }
}

export default Logo;
