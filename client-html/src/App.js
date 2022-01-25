import { RepoController } from "/src/controllers/RepoController.js"

export function App() {
    async function main() {
        const repoController = new RepoController();
        repoController.init();
    }

    main();
}