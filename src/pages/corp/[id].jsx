import styled from '@emotion/styled';
import { COLOR } from '../../constants'

import InfoBanner from '../../components/company/InfoBanner';
import Detail from '../../components/company/Detail';

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
    <Contents>
      <Details>
        <Detail
          kind="condition"
        />
        <Detail
          kind="worksupport"
        />
        <Detail
          kind="support"
        />
        <Detail
          kind="environment"
        />
        <Detail
          kind="etc"
        />
      </Details>
      <ReportBtn type="button">
      <img src="/images/icons/msg-exclam.svg" />
        오류 수정 요청
      </ReportBtn>
    </Contents>
  </>
  )
}

const Contents = styled.main`
  margin: 40px auto;
  max-width: 1024px;
  @media (max-width: 1024px) {
    padding: 0 30px;
  } ;
`;

const Details = styled.section`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 30px;
  @media (max-width: 1024px) {
    display: flex;
    flex-direction: column;
  } ;
`;

const ReportBtn = styled.button`
  display: flex;
  align-items: center;
  gap: 6px;
  margin: 30px auto;
  padding: 7px 25px;
  background-color: #fff;
  color: #FF3D00;
  font-size: 15px;
  font-weight: 600;
  border: 2px solid #FF3D00;
  border-radius: 20px;
  opacity: .5;
  img {
    width: 17px;
  }
  &:hover {
    opacity: 1;
  }
`;