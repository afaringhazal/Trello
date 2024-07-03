'use client'

import {
  Container,
  IconButton,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Tooltip,
  Typography,
  Box,
  CssBaseline
} from '@mui/material'

import React, { useState } from 'react'

import { User } from '@/types/user'
import moment from 'moment'

interface TabPanelProps {
  children?: React.ReactNode
  index: number
  value: number
}

export default function UserCards({ user }: { user: User | undefined }) {
  const [value, setValue] = useState(0)

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  }

  return (
    <>
      <Container maxWidth='xl'>
        <CssBaseline />
        <TableContainer component={Paper}>
          <Table
            sx={{ minWidth: 650 }}
            size='small'
            aria-label='a dense table'
          >
            <TableHead>
              <TableRow>
                <TableCell>Card</TableCell>
                <TableCell align='center'></TableCell>
                <TableCell align='center'>List</TableCell>
                <TableCell align='center'>Labels</TableCell>
                <TableCell align='center'>Due date</TableCell>
                <TableCell align='center'>Board</TableCell>
                <TableCell align='center'></TableCell>
                <TableCell align='center'></TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    </>
  )
}
