import styled from '@emotion/styled';
import { COLOR } from '../../constants'

import InfoBanner from '../../components/company/InfoBanner';

export default function Home() {
  return (
  <> 
    <InfoBanner
      logo="https://media-exp1.licdn.com/dms/image/C560BAQFXeEWM-FoApw/company-logo_200_200/0/1519881499181?e=2147483647&v=beta&t=KM_FX6hrlfp-OCrbSa6qrckrxs_znCgT6oyrxEP_0RI"
      name="우아한형제들"
      stock={false}
      good={23}
      category="IT/기술"
    />
    <Wrap>
      contents
    </Wrap>
  </>
  )
}

const Wrap = styled.main`
  margin: 25px auto;
  max-width: 1024px;
`;