import "./App.css";

import React from "react";
import Header from "./commons/Header";
import Body from "./commons/Body";
import Footer from "./commons/Footer";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import DramaDetail from "./pages/DramaDetail";
import Search from "./pages/Search";
import Commu from "./pages/Commu";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import SearchResults from "./pages/Search-results";
import Mypage from "./pages/Mypage";
import {AuthProvider} from "./AuthContext";


function App() {
  return (
    <AuthProvider>
      <div className="App">
        <BrowserRouter>
          <div className="main-container">
            <div className="header-body">
              <Header />
              <Routes>
                <Route path="/" element={<Body />} />
                <Route path="/drama/:id" element={<DramaDetail />} />
                <Route path="/search" element={<Search />} />
                <Route path="/search-results" element={<SearchResults />} />
                <Route path="/community" element={<Commu />} />
                <Route path="/login" element={<Login />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/mypage" element={<Mypage />} />
              </Routes>
            </div>
            <Footer />
          </div>
        </BrowserRouter>
      </div>
    </AuthProvider>
  );
}

export default App;
