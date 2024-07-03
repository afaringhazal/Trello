'use client'

import {
  Container,
  CssBaseline,
  Box,
  Typography,
  Avatar,
  Divider,
  TextField,
} from '@mui/material'

import moment from 'moment'

import { User } from '@/types/user'

export default function Edit({ user }: { user: User | undefined }) {
  return (
    <>
        <TextField
          margin='normal'
          required
          fullWidth
          name='bio'
          label='Bio'
          type='bio'
          id='bio'
          autoComplete='bio'
        />
    </>
  )
}
