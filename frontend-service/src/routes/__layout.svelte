<script>
    import '../app.scss';
</script>

<sveelte:head>
    <link crossorigin="anonymous" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" rel="stylesheet"/>
</sveelte:head>


<div class="container">
    <div class="bg-primary bg-gradient text-white my-3 p-3 rounded box-shadow d-flex align-items-center">
        <i class="fal fa-comments-alt fa-4x"></i>
        <div class="mx-3 lh-1">
            <h3 class="">FORO</h3>
            <small>Descripción del foro</small>
        </div>
    </div>
    <slot/>
</div>
