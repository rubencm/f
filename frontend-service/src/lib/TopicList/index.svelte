<script>
	import TopicRow from './TopicRow.svelte';

	export let topics;
</script>

{#if topics.length === 0}
    No hay temas
{:else}
    <table class="table table-hover">
        <thead class="">
        <tr>
            <th style="width: 50%">Tema</th>
            <th class="text-center" style="width: 20%">Creado</th>
            <th class="text-center" style="width: 10%">Número de mensajes</th>
            <th class="text-center" style="width: 20%">Ultimo mensaje</th>
        </tr>
        </thead>
        <tbody class="">
        {#each topics as topic (topic.id)}
            <TopicRow {topic}/>
        {/each}
        </tbody>
    </table>
{/if}