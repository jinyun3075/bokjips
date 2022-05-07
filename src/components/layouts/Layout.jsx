import React from 'react';
import { Header, Footer } from './';
import { Title } from './partials/Title';

export const Layout = ({ children }) => {
    return (
    <>
        <Title title="복지편살" />
        <Header />
            <section className="max-width">{children}</section>
        <Footer />
    </>
    );
};