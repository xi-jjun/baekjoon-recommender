import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import * as Styled from '../Default';

const HeaderTypo = ({ name, onClick }) => {
    const [mouseOver, setMouseOver] = useState(false);
    const mouseGetOver = () => {
        setMouseOver(true);
    }
    const mouseGetOut = () => {
        setMouseOver(false);
    }

    return (
        <Styled.HeaderTypo
            onMouseOver={mouseGetOver}
            onMouseOut={mouseGetOut}
            style={{
                textDecoration: mouseOver ? 'underline' : '',
                cursor: mouseOver ? 'pointer' : ''
            }}
            onClick={onClick}>
            {name}
        </Styled.HeaderTypo>
    )
}

const Header = () => {
    const navigate = useNavigate();

    return (
        <div>
            <Styled.HeaderContainer>
                <div>Logo</div>
                <Styled.HeaderTypoContainer>
                    <HeaderTypo name="Main" onClick={() => {
                        navigate("/", { replace: true });
                    }} />
                    <HeaderTypo name="Rival" onClick={() => {
                        navigate("/rival", { replace: true });
                    }} />
                    <HeaderTypo name="Solved" onClick={() => {
                        navigate("/solved", { replace: true });
                    }} />
                    <HeaderTypo name="Logout" onClick={() => {
                        localStorage.removeItem("Authorization");
                        navigate("/user/login", { replace: true });
                    }} />
                    <HeaderTypo name="MyPage" onClick={() => {
                        navigate("/user/my-page", { replace: true });
                    }} />
                </Styled.HeaderTypoContainer>
            </Styled.HeaderContainer>
        </div>
    )
}

export default Header