import styled from '@emotion/styled';
import { COLOR } from '../constants'

import Banner from '../components/main/Banner';
import ItemList from '../components/main/ItemList';

export default function Home() {
  return (
  <> 
    <Banner />
    <Wrap>
      <ItemList />
    </Wrap>
  </>
  )
}

const Wrap = styled.main`
  margin: 25px auto;
  max-width: 1024px;
`;