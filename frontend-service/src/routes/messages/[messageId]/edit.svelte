<script context="module">
    export async function load({page, fetch}) {
        const {messageId} = page.params;
        const message = await fetch('http://localhost:8080/messages/' + messageId).then((r) => r.json());

        return {
            props: {
                message
            }
        };
    }
</script>

<script>
    import MessageForm from "$lib/MessageForm.svelte";

    export let message;

</script>

<svelte:head>
    <title>{message.title} - Editar mensaje</title>
</svelte:head>

<div class="bg-white shadow p-4">
    <div class="d-flex">
        <h4><a href="/"><i class="fas fa-home"></i></a> - Editar mensaje</h4>
    </div>
    <MessageForm message={message} topicId={message.topicId}/>
</div>