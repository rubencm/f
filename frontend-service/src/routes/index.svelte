<script context="module">
    export async function load({page, fetch}) {
        const forums = await fetch('http://localhost:8080/forums/').then((r) => r.json());

        return {
            props: {
                forums,
            }
        };
    }
</script>

<script>
    import ForumList from "$lib/ForumList/index.svelte";

    export let forums;
</script>

<svelte:head>
    <title>Índice de foros</title>
</svelte:head>


<h4>Listado de foros</h4>
<ForumList {forums}/>