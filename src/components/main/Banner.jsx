import styled from '@emotion/styled'
import { COLOR } from '../../constants'

export default function Banner() {
  return (
    <Container>
      <Wrap>
        <Item>Hello</Item>
      </Wrap>
    </Container>
  )
}

const Container = styled.section`
  width: 100%;
  height: 210px;
  background-color: ${COLOR.main};
`

const Wrap = styled.div`
  margin: 0 auto;
  padding: 20px 0;
  max-width: 1024px;
  height: 100%;
`

const Item = styled.article`
  display: block;
  padding: 15px;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 15px;
`
