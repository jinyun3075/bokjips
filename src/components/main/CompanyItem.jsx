import Link from 'next/link'
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
      <Logo>
        <img src={logo} alt={name} />
      </Logo>
      <Info>
        <TitleWrap>
          <Name>{name}</Name>
          <InfoTag>{stock ? '상장' : '비상장'}</InfoTag>
          <InfoTag>{category}</InfoTag>
        </TitleWrap>
        <Tags>
          {taglist} Lorem ipsum dolor sit, amet consectetur adipisicing elit.
          Nobis porro sint voluptatem tempora officiis atque asperiores, alias
          voluptatibus facilis, magni maxime quis unde, corrupti quod harum
          labore a necessitatibus ipsum.
        </Tags>
      </Info>

      <Options>
        <Like>♥ {like}</Like>
        <Link href={`/corp/${companyId}`}>
          <Btn>복지 정보 자세히</Btn>
        </Link>
        <Btn>채용정보 보기</Btn>
      </Options>
    </Container>
  )
}

const Container = styled.li`
  position: relative;
  margin: 3px 0;
  padding: 18px;
  width: 100%;
  height: 150px;
  border-radius: 10px;
  box-shadow: 0 0 16px 2px rgba(0, 0, 0, 0.1);
  &:hover {
    transition: 0.3s transform;
    transform: scale(1.03);
  }
`

const Logo = styled.div`
  position: relative;
  display: inline-block;
  width: 115px;
  height: 115px;
  border: 1px solid ${COLOR.gray};
  border-radius: 100%;
  overflow: hidden;
  img {
    position: absolute;
    width: 90px;
    height: 90px;
    top: 50%;
    left: 50%;
    object-fit: cover;
    border-radius: 100%;
    transform: translate(-50%, -50%);
  }
`
const Info = styled.div`
  position: absolute;
  display: inline-block;
  padding: 0 17px;
  top: 50%;
  width: 70%;
  transform: translateY(-50%);
`
const TitleWrap = styled.div`
  display: flex;
  margin-bottom: 9px;
  align-items: center;
  gap: 0 5px;
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
const Tags = styled.ul``
const Options = styled.div`
  float: right;
  width: 130px;
  display: flex;
  flex-direction: column;
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
