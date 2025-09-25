import Header from "@/components/Header.tsx";
import React from "react";
import Hero from "@/components/Hero.tsx";
import Footer from "@/components/Footer.tsx";

type  Props = {
 children: React.ReactNode;
}


export default function Layout({children}:Props) {
    return (
        <div className={"flex flex-col min-h-screen"}>
            <Header/>
            <Hero/>
            <div className={"container mx-auto flex-1 py-10"}>
                {children}
            </div>
            <Footer/>
        </div>
    );
}

