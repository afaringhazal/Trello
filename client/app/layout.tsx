import './globals.css'
import { Inter } from 'next/font/google'
import axios from 'axios'
axios.defaults.withCredentials = true

const inter = Inter({ subsets: ['latin'] })

export const metadata = {
  title: 'Trello'
}

export default function RootLayout({
  children
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        {children}
      </body>
    </html>
  )
}
