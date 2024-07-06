'use client'

import Header from "@/components/Header";
import { AppContext } from "@/context/AppContext";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
    const pages = [
        { title: 'Workspaces', href: '/dashboard/workspaces' },
        { title: 'Create', href: '/dashboard/create-workspace' },
    ]

    return (
        <>
            <AppContext>
                <Header
                    pages={pages}
                />
                {children}
            </ AppContext>
        </>
    )
}
