import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import "./Search-results.css";

function SearchResults() {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const query = queryParams.get("query");

  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const apiUrl = `http://belleravi.co.kr/api/tv?query=${query}`;

    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("API 요청 실패");
        }
        return response.json();
      })
      .then((data) => {
        setSearchResults(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("API 요청 실패:", error);
        setLoading(false);
      });
  }, [query]);

  if (loading) {
    return <p>검색 중...</p>;
  }

  return (
    <div className="search-results">
      <h2>검색 결과: {query}</h2>
      <ul className="search-result">
        {searchResults.map((result, index) => (
          <li className="search-result" key={index}>

              <a href={`http://belleravi.co.kr:3000/drama/${result.id}`}><img
              src={`https://image.tmdb.org/t/p/w200/${result.poster_path}`}
              alt={result.name}
            /></a>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default SearchResults;
