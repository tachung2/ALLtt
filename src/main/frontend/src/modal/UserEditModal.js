import React from "react";
import "./UserEditModal.css";

const userEditModal = ({ isOpen, onClose}) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="X-btn" onClick={onClose}>X</button>
                <div className="modal-title">내 정보 수정</div>

            </div>
        </div>
    );
}
export default userEditModal;