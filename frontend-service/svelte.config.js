import preprocess from 'svelte-preprocess';
import adapter from '@sveltejs/adapter-node';

/** @type {import('@sveltejs/kit').Config} */
const config = {
    kit: {
        // hydrate the <div id="svelte"> element in src/app.html
        target: '#svelte',

        vite: {
            css: {
                preprocessorOptions: {
                    scss: {
                        additionalData: '@import "src/variables.scss";'
                    }
                }
            }
        },

        adapter: adapter(),
        ssr: false /* Habria que configurar bien el tema de las rutas en los entornos */
    },

    preprocess: [
        preprocess({
            postcss: true,

            scss: {
                prependData: '@import "src/variables.scss";'
            }
        })
    ]
};

export default config;
