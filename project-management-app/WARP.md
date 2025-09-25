# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a project management application built as a Next.js 15 web application with a modern React 19 frontend. The project follows a client-server architecture with the frontend located in the `client/` directory.

### Tech Stack

- **Frontend Framework**: Next.js 15.5.2 with App Router
- **React Version**: React 19.1.0 (latest stable)
- **Language**: TypeScript 5.x with strict mode enabled
- **Styling**: TailwindCSS 4.x with PostCSS integration
- **Linting**: ESLint 9.x with Next.js and TypeScript rules
- **Package Manager**: pnpm (lockfile present)
- **Build System**: Turbopack (Next.js built-in bundler)

## Development Commands

All commands should be run from the `client/` directory:

```powershell
# Navigate to client directory
cd client

# Install dependencies
pnpm install

# Start development server (with Turbopack for faster builds)
pnpm dev

# Build for production (with Turbopack)
pnpm build

# Start production server
pnpm start

# Run ESLint
pnpm lint

# Run ESLint with auto-fix
pnpm lint --fix
```

## Architecture

### Directory Structure

```
project-management-app/
├── client/                     # Next.js frontend application
│   ├── src/
│   │   └── app/               # Next.js App Router directory
│   │       ├── layout.tsx     # Root layout component
│   │       ├── page.tsx       # Home page component
│   │       ├── globals.css    # Global styles with TailwindCSS
│   │       └── favicon.ico    # Site icon
│   ├── public/                # Static assets
│   ├── package.json           # Dependencies and scripts
│   ├── tsconfig.json          # TypeScript configuration
│   ├── next.config.ts         # Next.js configuration
│   ├── eslint.config.mjs      # ESLint flat config
│   ├── postcss.config.mjs     # PostCSS config for TailwindCSS
│   └── pnpm-lock.yaml         # Package lockfile
```

### Key Configuration Details

- **TypeScript**: Configured with strict mode, ES2017 target, and path mapping (`@/*` → `./src/*`)
- **Next.js**: Using App Router architecture with TypeScript support
- **TailwindCSS**: Version 4.x with integrated PostCSS setup
- **ESLint**: Flat configuration extending Next.js core web vitals and TypeScript rules
- **Fonts**: Using Geist Sans and Geist Mono from Google Fonts

## Development Guidelines

### File Naming Conventions
- React components: Use PascalCase (e.g., `UserProfile.tsx`)
- Pages: Use lowercase with kebab-case for routes (following Next.js conventions)
- Utilities: Use camelCase (e.g., `formatDate.ts`)

### Import Paths
- Use path aliases: `@/` maps to `src/`
- Prefer absolute imports over relative imports for better maintainability

### Styling Approach
- Primary styling: TailwindCSS utility classes
- Global styles: Defined in `src/app/globals.css`
- Theme configuration: Uses CSS custom properties for colors and fonts

### TypeScript Standards
- Strict mode enabled - all types must be properly defined
- Use `type` for object shapes, `interface` for extensible contracts
- Define component prop types explicitly

## Testing

Currently, no testing framework is configured. When adding tests, consider:
- Jest + React Testing Library for unit/integration tests
- Playwright for e2e tests
- Add test scripts to `package.json`

## Common Development Tasks

### Adding New Pages
1. Create new files in `src/app/` directory
2. Use Next.js App Router file conventions (`page.tsx`, `layout.tsx`, `loading.tsx`)
3. Export default React component

### Adding Components
1. Create in `src/components/` (directory doesn't exist yet but should be created)
2. Use TypeScript interfaces for props
3. Apply TailwindCSS classes for styling

### Environment Configuration
- Next.js environment variables should be prefixed with `NEXT_PUBLIC_` for client-side access
- Add environment files: `.env.local`, `.env.development`, `.env.production`

### Performance Considerations
- Next.js 15 uses Turbopack by default for faster builds
- App Router provides automatic code splitting
- Use Next.js Image component for optimized images
- Leverage React 19's concurrent features

## Project Status

This appears to be a newly created project with the default Next.js starter template. The actual project management functionality is yet to be implemented. Future development should focus on:

1. Setting up the project management domain models
2. Implementing user authentication
3. Creating task/project management UI components
4. Adding backend API integration
5. Setting up database connectivity (likely in a separate backend service)
