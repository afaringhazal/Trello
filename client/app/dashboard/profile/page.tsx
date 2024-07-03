'use client'

import {
  Container,
  Alert,
  Snackbar,
  AlertTitle,
  Tabs,
  Tab,
  Box,
} from '@mui/material'

import React, { useEffect, useState } from 'react'
import { axios } from '@/utils'
import { AxiosError } from 'axios'
import moment from 'moment'

import { useRouter } from 'next/navigation'
import { User } from '@/types/user'

import Edit from './edit'
import UserInfo from './user'
import UserCards from './cards'

interface TabPanelProps {
  children?: React.ReactNode
  index: number
  value: number
}

export default function Profile() {
  const [alertError, setAlertError] = useState<string>('')
  const [userInfo, setUserInfo] = useState<User>()

  const router = useRouter()

  useEffect(() => {
    getMe().then((data) => {
      setUserInfo(data)
    })
  }, [])

  const [value, setValue] = useState(0)

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  }

  return (
    <>
      <Container maxWidth='xl'>
        <UserInfo user={userInfo} />
        <Box sx={{ width: '100%', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <Box sx={{ borderBottom: 1, borderColor: 'divider', width: '100%' }}>
            <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
              <Tab label="Profile and visibility" {...allyProps(0)} />
              <Tab label="Cards" {...allyProps(1)} />
            </Tabs>
          </Box>
          <CustomTabPanel value={value} index={0}>
            <Edit user={userInfo} />
          </CustomTabPanel>
          <CustomTabPanel value={value} index={1}>
            <UserCards user={userInfo} />
          </CustomTabPanel>
        </Box>
      </Container>
      {alertError && (
        <Snackbar
          open={!!alertError}
          autoHideDuration={3000}
          onClose={() => setAlertError('')}
        >
          <Alert severity='error'>
            <AlertTitle>Error</AlertTitle>
            {alertError}
          </Alert>
        </Snackbar>
      )}
    </>
  )

  async function getMe(): Promise<User | undefined> {
    try {
      return (await axios.get('/me')).data
    } catch (e: AxiosError | any) {
      if (e.response?.status === 401) {
        router.push('/auth-login')
        return
      }
      setAlertError(e.response?.data?.message || e.message)
    }
  }
}

function CustomTabPanel(props: TabPanelProps) {
  const { children, value, index, ...other } = props
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  )
}

function allyProps(index: number) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  }
}

