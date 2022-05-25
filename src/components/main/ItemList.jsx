import styled from '@emotion/styled'
import { COLOR } from '../../constants'
import CompanyItem from './CompanyItem'

export default function ItemList() {
  return (
    <>
      <Items>
        <CompanyItem
          companyId={1}
          name="우아한형제들"
          logo="https://media-exp1.licdn.com/dms/image/C560BAQFXeEWM-FoApw/company-logo_200_200/0/1519881499181?e=2147483647&v=beta&t=KM_FX6hrlfp-OCrbSa6qrckrxs_znCgT6oyrxEP_0RI"
          stock={true}
          category="IT"
          taglist="taglist"
          like="201"
          link=""
        />
        <CompanyItem
          companyId={1}
          name="직방"
          logo="logo url"
          stock={true}
          category="IT"
          taglist="taglist"
          like="201"
          link=""
        />
      </Items>
    </>
  )
}

const Items = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 23px 0;
`
