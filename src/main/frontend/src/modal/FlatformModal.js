import React, {useState} from "react";
import "./UserEditModal.css";
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const FlatformModal = ({ isOpen, onClose}) => {
    const [startDate, setStartDate] = useState(new Date());

    const handleSubmit = async (event) => {
        event.preventDefault(); // 기본 폼 제출 동작을 방지

        const payload = {
            startDate: startDate,
        };

        try {
            const response = await fetch('http://belleravi.co.kr/api/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
            });

            const data = await response.json();
            if (response.ok) {
                console.log("ok")
                onClose();
            } else {
                // 로그인 실패 처리 (예: 오류 메시지 표시)
                console.error(data.message);
                onClose();
            }
        } catch (error) {
            console.error('날짜 보내는 중 에러', error);
            onClose();
        }
    };
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="X-btn" onClick={onClose}>X</button>
                <form onSubmit={handleSubmit}>
                    <div className="modal-title">구독일 선택</div>
                    <DatePicker selected={startDate} onChange={date => setStartDate(date)} />
                    <input type="submit" className="date-btn" value="확인"/>
                </form>
            </div>
        </div>
    );
}
export default FlatformModal;