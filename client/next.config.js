/** @type {import('next').NextConfig} */
const nextConfig = {
    experimental: { appDir: true, serverComponentsExternalPackages: ["mongoose"] },
    webpack(config) {
        config.experiments = { ...config.experiments, topLevelAwait: true };
        return config;
    },
    publicRuntimeConfig: {
        backendUrl: 'http://localhost:8080',
    },
    async rewrites() {
        return [
            {
                source: '/api/:path*',
                destination: 'http://localhost:8080/api/:path*',
            },
            {
                source: '/:path*',
                destination: 'http://localhost:8080/api/:path*',
            },
        ]
    },
}

module.exports = nextConfig
