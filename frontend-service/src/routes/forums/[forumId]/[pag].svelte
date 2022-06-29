<script context="module">
    export async function load({page, fetch}) {
        const {forumId, pag} = page.params;
        const [forum, topics] = await Promise.all([
            fetch('http://localhost:8080/forums/' + forumId).then((r) => r.json()),
            fetch('http://localhost:8080/topics/byforum/' + forumId + '?page=' + pag).then((r) => r.json())
        ]);

        return {
            props: {
                forum,
                topics,
                forumId,
                pag: parseInt(pag)
            }
        };
    }
</script>

<script>
    import Pagination from "$lib/Pagination.svelte";
    import TopicList from "$lib/TopicList/index.svelte";

    export let forum;
    export let topics;
    export let forumId;
    export let pag;

</script>

<svelte:head>
    <title>Ver foro - {forum.name}</title>
</svelte:head>

<div class="bg-white shadow p-4">
    <div class="d-flex">
        <h4><a href="/"><i class="fas fa-home"></i></a> &#8594; <a href="/forums/{forumId}/0">{forum.name}</a></h4>
        <a class="btn btn-primary ms-auto" href="/forums/{forumId}/new-topic" role="button">Nuevo tema</a>
    </div>
    <TopicList {topics}/>
    <Pagination numElements={forum.numTopics} {pag}/>
</div>