import styled from '@emotion/styled'
import { COLOR } from '../../constants'

const DataKinds = {
  condition: ['근무 조건', '#E86A6A'],
  worksupport: ['근무 지원', '#5EAC7D'],
  support: ['근무 외 지원', '#855EAC'],
  environment: ['사내 환경 지원', '#DE9528'],
  etc: ['기타 사항', '#898989'],
}

export default function Detail({ kind, data }) {
  return (
    <Container>
      <Title>{DataKinds[kind][0]}</Title>
      <List>
        <SubList>
          <SubTitle color={DataKinds[kind][1]}>유연근무제</SubTitle>
          <OptionTxt color={DataKinds[kind][1]}>텍스트예시</OptionTxt>
        </SubList>
      </List>
    </Container>
  )
}

const Container = styled.article`
  padding: 23px;
  border-radius: 5px;
  box-shadow: 0 0 10px 3px rgba(0, 0, 0, 0.1);
`
const Title = styled.h4`
  font-weight: 700;
  font-size: 25px;
`

const List = styled.ul`
  padding: 20px 0 5px 0;
`
const SubList = styled.li`
  margin: 0 0 10px 0;
`
const SubTitle = styled.span`
  display: inline-block;
  margin-right: 7px;
  padding: 6px 8px;
  background-color: ${props => props.color};
  color: #fff;
  font-size: 17px;
  font-weight: 500;
  border-radius: 15px;
`
const OptionTxt = styled.span`
  color: ${props => props.color};
  font-size: 17px;
`
