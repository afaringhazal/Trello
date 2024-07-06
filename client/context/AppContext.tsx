import { AppContextType } from '@/types/appContext'
import { User } from '@/types/user'
import { axios } from '@/utils'
import { AxiosError } from 'axios'
import { useRouter } from 'next/navigation'
import React, { createContext, useEffect, useState } from 'react'

const context: AppContextType = {
  user: undefined,
  setUser: () => { }
}
const Context = createContext<AppContextType>(context)

function AppContext({ children }: { children: React.ReactNode }) {
  const [user, setUser] = useState<User>()
  const router = useRouter()

  axios?.interceptors.response.use(
    response => response,
    function logoutOnForbidden(e) {
      if ([401, 403].includes(e.response?.status)) {
        router.push('/auth-login')
      }
      return Promise.reject(e)
    }
  )


  useEffect(() => {
    getMe().then((user) => {
      setUser(user)
    })
  }, [])

  useEffect(() => {
    context.user = user
    context.setUser = setUser
  }, [user])

  return (
    <Context.Provider
      value={{
        user,
        setUser,
      }}
    >
      {children}
    </Context.Provider>
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

export { AppContext, Context }
