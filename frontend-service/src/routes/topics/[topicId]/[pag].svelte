<script context="module">
    export async function load({page, fetch}) {
        const {topicId, pag} = page.params;
        const [topic, messages] = await Promise.all([
            fetch('http://localhost:8080/topics/' + topicId).then((r) => r.json()),
            fetch('http://localhost:8080/messages/bytopic/' + topicId + '?page=' + pag).then((r) => r.json())
        ]);
        const forum = await fetch('http://localhost:8080/forums/' + topic.forumId).then((r) => r.json());

        return {
            props: {
                forum,
                topic,
                messages,
                topicId,
                pag: parseInt(pag)
            }
        };
    }
</script>

<script>
    import MessageList from "$lib/MessageList/index.svelte";
    import Pagination from "$lib/Pagination.svelte";

    export let forum;
    export let topic;
    export let messages;
    export let topicId;
    export let pag;

</script>

<svelte:head>
    <title>Ver tema - {topic.title} - Pagina {pag+1}</title>
</svelte:head>

<div class="bg-white shadow p-4">
    <div class="d-flex">
        <h4><a href="/"><i class="fas fa-home"></i></a> &#8594; <a href="/forums/{forum.id}/0">{forum.name}</a> &#8594;
            <a href="/topics/{topicId}/0">{topic.title}</a></h4>
        <a class="btn btn-primary ms-auto" href="/topics/{topicId}/add-response" role="button">Nueva respuesta</a>
    </div>
    <MessageList {messages}/>
    <Pagination numElements={topic.numMessages} {pag}/>
</div>