import styled from '@emotion/styled'
import { COLOR } from '../../constants'

export default function CompanyItem({
  companyId,
  name,
  logo,
  stock,
  category,
  taglist,
  like,
  link,
}) {
  return (
    <Container>
      <Logo>{logo}</Logo>

      <Info>
        <TitleWrap>
          <Name>{name}</Name>
          <InfoTag>{stock ? '상장' : '비상장'}</InfoTag>
          <InfoTag>{category}</InfoTag>
        </TitleWrap>
        <List>{taglist}</List>
      </Info>

      <Options>
        <Like>♥ {like}</Like>
        <Btn>복지 정보 자세히</Btn>
        <Btn>채용정보 보기</Btn>
      </Options>
    </Container>
  )
}

const Container = styled.article`
  margin: 3px 0;
  display: flex;
  padding: 18px;
  width: 100%;
  height: 150px;
  border-radius: 20px;
  box-shadow: 0 0 7px 1px rgba(0, 0, 0, 0.25);
`

const Logo = styled.div`
  flex: 1;
`
const Info = styled.div`
  display: flex;
  flex-direction: column;
  flex: 4;
  gap: 14px;
`
const TitleWrap = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
`
const Name = styled.h3`
  font-weight: 800;
  font-size: 25px;
`
const InfoTag = styled.span`
  min-width: 60px;
  padding: 3px 8px;
  background-color: ${COLOR.gray};
  font-size: 18px;
  text-align: center;
  border-radius: 15px;
`
const List = styled.ul``
const Options = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  gap: 8px;
  justify-content: center;
  align-items: center;
`
const Like = styled.span`
  color: ${COLOR.main};
  font-size: 23px;
  font-weight: 700;
`
const Btn = styled.button`
  width: 100%;
  height: 35px;
  font-size: 17px;
  font-weight: 700;
  color: ${COLOR.main};
  border-radius: 12px;
  border: 2px solid ${COLOR.main};
`
