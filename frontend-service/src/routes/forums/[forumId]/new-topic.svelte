<script context="module">
    export async function load({page, fetch}) {
        const {forumId} = page.params;
        const forum = await fetch('http://localhost:8080/forums/' + forumId).then((r) => r.json());

        return {
            props: {
                forum,
                forumId
            }
        };
    }
</script>

<script>
    import MessageForm from "$lib/MessageForm.svelte";

    export let forumId;
</script>

<svelte:head>
    <title>Crear nuevo tema</title>
</svelte:head>

<div class="bg-white shadow p-4">
    <div class="d-flex">
        <h4><a href="/"><i class="fas fa-home"></i></a> &#8594; Crear nuevo tema</h4>
    </div>
    <MessageForm {forumId}/>
</div>