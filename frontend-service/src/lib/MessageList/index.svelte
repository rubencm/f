<script>
    import MessageRow from "./MessageRow.svelte";

    export let messages;
</script>


{#if messages.length === 0}
    No hay mensajes
{:else}
    {#each messages as message (message.id)}
        <MessageRow {message}/>
    {/each}
{/if}