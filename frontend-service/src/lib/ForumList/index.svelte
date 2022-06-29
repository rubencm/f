<script>
    import ForumRow from "./ForumRow.svelte";

    export let forums;
</script>


{#if forums.length === 0}
    No hay foros
{:else}
    <div class="container">
        <div class="row">
            {#each forums as forum (forum.id)}
                <ForumRow {forum}/>
            {/each}
        </div>
    </div>
{/if}