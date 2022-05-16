import styled from '@emotion/styled';
import { COLOR } from '../../constants'
import CompanyItem from './CompanyItem';

export default function ItemList() {
    return (
        <>
            <CompanyItem 
            companyId = ""
            name = "직방"
            logo = "logo url"
            stock = {true}
            category = "IT"
            taglist = "taglist"
            like = "201"
            link = ""
            />
        </>
    )
}
