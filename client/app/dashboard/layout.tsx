'use client'

import Header from "@/Components/Header";
import { User } from "@/types/user";
import { axios } from "@/utils";
import { AxiosError } from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
    const router = useRouter()
    const [userInfo, setUserInfo] = useState<User>()
    const pages = [
        { title: 'Workspaces', href: '/workspaces' },
        { title: 'Create', href: '/create-workspace' },
    ]

    useEffect(() => {
        getMe().then(setUserInfo)
    }, [])

    return (
        <>
            <Header
                pages={pages}
                user={userInfo}
            />
            {children}
        </>
    )

    async function getMe(): Promise<User | undefined> {
        try {
            return (await axios.get('/me')).data
        } catch (e: AxiosError | any) {
            if ([401, 403].includes(e.response?.status)) {
                router.push('/auth-login')
                return
            }
        }
    }
}
