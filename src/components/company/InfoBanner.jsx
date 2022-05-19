import styled from '@emotion/styled';
import { COLOR } from '../../constants';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as faHeartS } from '@fortawesome/free-solid-svg-icons'
import { faHeart as faHeartR } from '@fortawesome/free-regular-svg-icons'


export default function InfoBanner({
    stock, category, logo, name, goodCount, goodCheck
}) {
    return (
        <Container>
            <Wrap>
                <Logo><LogoImg src={logo} /></Logo>
                <Info>
                    <CategoryList>
                        <Category>{stock ? "상장" : "비상장"}</Category>
                        <Category>{category}</Category>
                    </CategoryList>
                    <CorpName>{name}</CorpName>
                    <BtnWrap>
                        <LinkBtn>기업 사이트</LinkBtn>
                        <LinkBtn>채용정보 보기</LinkBtn>
                    </BtnWrap>
                </Info>
                <Good>
                    <GoodBtn
                        type="button"
                        onClick={() => {alert("좋아요 클릭")}}
                    >
                    <FontAwesomeIcon icon={faHeartR} /> {goodCount}
                    </GoodBtn>
                </Good>
            </Wrap>
        </Container>
    )
}

const Container = styled.section`
    width: 100%;
    height: 210px;
    background-color: ${COLOR.main};
`;

const Wrap = styled.div`
    position: relative;
    margin: 0 auto;
    max-width: 1100px;
    height: 100%;
    overflow: hidden;
`;

const Logo = styled.div`
    display: flex;
    position: absolute;
    justify-content: center;
    align-items: center;
    top: 50%;
    width: 260px;
    height: 260px;
    border-radius: 100%;
    background: linear-gradient(90deg, rgba(255,255,255,0) 20%, rgba(255,255,255,0.45) 65%, rgba(255,255,255,1) 100%);
    transform: translate(0, -50%);
`;
const LogoImg = styled.img`
    width: 100px;
    height: 100px;
    border-radius: 20px;
    object-fit: cover;
`;

const Info = styled.div`
    display: flex;
    height: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 15px;
`;
const Good = styled.div`
    position: absolute;
    top: 50%;
    right: 0;
    width: 260px;
    height: 260px;
    border: 1px solid #fff;
    border-radius: 100%;
    transform: translate(0, -50%);
    overflow: hidden;
`;
const GoodBtn = styled.button`
    width: 100%;
    color: #fff;
    font-size: 35px;
    font-weight: 600;
    line-height: 260px;
    &:hover {
        background-color: #fff;
        color: ${COLOR.main};
        transition: .3s background-color;
    }
`;

const CategoryList = styled.ul``;
const Category = styled.li`
    display: inline-block;
    margin: 0 4px;
    padding: 3px 12px;
    color: #fff;
    border: 1px solid #fff;
    border-radius: 15px;
`;
const CorpName = styled.h3`
    color: #fff;
    font-size: 42px;
    font-weight: 700;
`;
const BtnWrap = styled.div``;
const LinkBtn = styled.button`
    margin: 0 7px;
    padding: 10px 26px;
    color: ${COLOR.main};
    font-size: 16px;
    font-weight: 700;
    background-color: #fff;
    border-radius: 8px;
    &:hover {
        opacity: .85;
    }
`;