import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import basicSsl from "@vitejs/plugin-basic-ssl"
import tailwindcss from "tailwindcss";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), basicSsl()],
  css: {
    postcss: {
      plugins: [tailwindcss()],
    },
  },
  server: {
    hmr: {
      host: "localhost",
    },
  },
})