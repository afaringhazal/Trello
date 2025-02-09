'use client'

import {
	AppBar,
	Toolbar,
	Container,
	Typography,
	Box,
	Tooltip,
	Menu,
	IconButton,
	Button,
	MenuItem,
	Avatar
} from '@mui/material'
import AdbIcon from '@mui/icons-material/Adb'
import MenuIcon from '@mui/icons-material/Menu'
import { useContext, useState } from 'react'
import Link from 'next/link'
import { useRouter } from 'next/navigation'
import { Context } from '@/context/AppContext'

export default function Header({ pages }: { pages: { title: string, href: string }[] }) {
	const router = useRouter()
	const { user } = useContext(Context)

	const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null)
	const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null)

	const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
		setAnchorElNav(event.currentTarget)
	}
	const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
		setAnchorElUser(event.currentTarget)
	}

	const handleCloseNavMenu = () => {
		setAnchorElNav(null)
	}

	const handleCloseUserMenu = () => {
		setAnchorElUser(null)
	}

	const settings = ['Profile', 'Logout']
	const settingsActions = [() => { router.push('/dashboard/profile') }, handleCloseUserMenu]

	return (
		<AppBar position='static'>
			<Container maxWidth='xl'>
				<Toolbar>
					<AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
					<Typography
						variant='h6'
						noWrap
						component='a'
						href='/'
						sx={{
							mr: 2,
							display: { xs: '', md: 'flex' },
							fontFamily: 'sans-serif',
							fontWeight: 700,
							letterSpacing: '.2rem',
							color: 'inherit',
							textDecoration: 'none'
						}}
					>
						Trello
					</Typography>
					<Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
						<IconButton
							size='large'
							aria-label='account of current user'
							aria-controls='menu-appbar'
							aria-haspopup='true'
							onClick={handleOpenNavMenu}
							color='inherit'
						>
							<MenuIcon />
						</IconButton>
						<Menu
							id='menu-appbar'
							anchorEl={anchorElNav}
							anchorOrigin={{
								vertical: 'bottom',
								horizontal: 'left'
							}}
							keepMounted
							transformOrigin={{
								vertical: 'top',
								horizontal: 'left'
							}}
							open={Boolean(anchorElNav)}
							onClose={handleCloseNavMenu}
							sx={{
								display: { xs: 'block', md: 'none' }
							}}
						>
							{pages.map((page) => (
								<MenuItem
									key={page.title}
									onClick={handleCloseNavMenu}
								>
									<Typography textAlign='center'>
										<Link
											href={page.href}
											passHref
											key={page.title}
											legacyBehavior
										>
											<a
												style={{
													textDecoration: 'none',
													color: '#000'
												}}
											>
												{page.title}
											</a>
										</Link>
									</Typography>
								</MenuItem>
							))}
						</Menu>
					</Box>
					<Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
						{pages.map((page) => (
							<Button
								key={page.title}
								onClick={handleCloseNavMenu}
								href={page.href}
								sx={{ my: 2, color: 'white', display: 'block' }}
							>
								{page.title}
							</Button>
						))}
					</Box>

					<Box sx={{ flexGrow: 0 }}>
						<Tooltip title='User settings'>
							<IconButton
								onClick={handleOpenUserMenu}
								sx={{ p: 0, transform: 'scale(2.0)' }}
							>
								<Typography component='h1' variant='h1'>
									<Avatar sx={{ width: 20, height: 20 }}>
										{user?.username?.[0] || ''}
									</Avatar>
								</ Typography>
							</IconButton>
						</Tooltip>
						<Menu
							sx={{ mt: '45px' }}
							id='menu-appbar'
							anchorEl={anchorElUser}
							anchorOrigin={{
								vertical: 'top',
								horizontal: 'right'
							}}
							keepMounted
							transformOrigin={{
								vertical: 'top',
								horizontal: 'right'
							}}
							open={Boolean(anchorElUser)}
							onClose={handleCloseUserMenu}
						>
							{settings.map((setting, i) => (
								<MenuItem key={setting} onClick={settingsActions[i]}>
									<Typography textAlign='center'>{setting}</Typography>
								</MenuItem>
							))}
						</Menu>
					</Box>
				</Toolbar>
			</Container>
		</AppBar>
	)
}
