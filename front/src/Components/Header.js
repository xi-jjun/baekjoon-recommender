import { useState } from 'react';
import * as Styled from '../Default';

const HeaderTypo = ({ name }) => {
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
            }}>
            {name}
        </Styled.HeaderTypo>
    )
}

const Header = () => {
    const clickHeader = () => {
        localStorage.removeItem("Authorization")
    };

    return (
        <div>
            <Styled.HeaderContainer>
                <div>Logo</div>
                <Styled.HeaderTypoContainer>
                    <Styled.StyledLink to="/"><HeaderTypo name="Main" /></Styled.StyledLink>
                    <Styled.StyledLink to="/rival"><HeaderTypo name="Rival" /></Styled.StyledLink>
                    <Styled.StyledLink to="/solved"><HeaderTypo name="Solved" /></Styled.StyledLink>
                    <Styled.StyledLink to="/user/login" onClick={clickHeader}><HeaderTypo name="Logout" /></Styled.StyledLink>
                    <Styled.StyledLink to="/user/my-page"><HeaderTypo name="MyPage" /></Styled.StyledLink>
                </Styled.HeaderTypoContainer>
            </Styled.HeaderContainer>
        </div>
    )
}

export default Header