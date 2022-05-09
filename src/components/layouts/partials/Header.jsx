import styled from '@emotion/styled';
import { COLOR } from '../../../constants'

export const Header = () => {
    return (
        <Container>
            <Title>
                <LogoType><strong>복지</strong><br />편살</LogoType>
                <Explain><strong>복</strong>잡한 <strong>복지</strong> 정보 <strong>편</strong>하게 <strong>살</strong>펴보자</Explain>
            </Title>
            <UserInfo>
                <InfoList>로그인</InfoList>
                <InfoList>회원정보</InfoList>
            </UserInfo>
        </Container>
    );
};

const Container = styled.header`
    margin: 25px auto 10px auto;
    max-width: 1024px;
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
`;

const Title = styled.div`
    flex: 2;
    display: flex;
    align-items: flex-end;
    gap: 10px;
    strong {
        color: ${COLOR.main};
    }
`;

const LogoType = styled.h1`
    float: left;
    font-weight: 800;
    font-size: 41px;
    line-height: 40px;
`;
const Explain = styled.p`
    font-size: 21px;
`;

const UserInfo = styled.ul`
    display: flex;
    gap: 15px;
`;
const InfoList = styled.li`
    
`;