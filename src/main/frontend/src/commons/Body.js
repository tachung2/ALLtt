import Best from "./Best";

import React from "react";
import { useState } from "react";

export default function Body() {
  const [flatforms] = useState([
    {
      id: "Netflex",
      text: "netflex",
    },
    {
      id: "Watcha",
      text: "watcha",
    },
    {
      id: "Disney+",
      text: "disney",
    },
    {
      id: "Tving",
      text: "tving",
    },
    {
      id: "Wavve",
      text: "wavve",
    },
    {
      id: "AppleTV+",
      text: "apple",
    },
  ]);
  return (
    <div className="body-main">
      <section className="banner">
        <img src="/vaikw.png" alt="배너" className="banner-img" />
      </section>
      <section className="best-list">
        {flatforms.map((flatform, i) => (
          <Best key={i} flatform={flatform.text} flatformname={flatform.id} />
        ))}
      </section>
    </div>
  );
}
