'use client'

import {
  Container,
  CssBaseline,
  Box,
  Typography,
  Avatar,
  Divider,
} from '@mui/material'

import moment from 'moment'

import { User } from '@/types/user'

export default function UserInfo({ user }: { user: User | undefined }) {
  return (
    <>
      <Container maxWidth='xl'>
        <CssBaseline />
        <Box sx={{ marginTop: 8, display: 'flex', alignItems: 'center' }} >
          <Avatar sx={{ width: 110, height: 110 }} >
            <Typography component='h1' variant='h2'>
              {user?.username?.[0]}
            </Typography>
          </Avatar>
          <Divider orientation='vertical' variant="middle" />
          <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', padding: '10px 10px 10px 10px', marginTop: '10px' }} >
            <Typography component='h1' variant='h3'>
              @{user?.username}
            </Typography>
            <Divider />
            <Typography component='center' variant='h5' justifyContent='right' display='flex'>
              {user?.email}
            </Typography>
            <Typography component='center' variant='overline' justifyContent='right' display='flex'>
              Joined {moment(user?.createdAt).format('MMM YYYY')}
            </Typography>
          </Box>
        </Box>
      </Container>
    </>
  )
}
